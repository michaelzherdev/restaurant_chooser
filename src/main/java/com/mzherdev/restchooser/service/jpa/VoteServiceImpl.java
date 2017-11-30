package com.mzherdev.restchooser.service.jpa;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.repository.VoteRepository;
import com.mzherdev.restchooser.service.VoteService;
import com.mzherdev.restchooser.util.DateTimeUtil;
import com.mzherdev.restchooser.util.JpaUtil;
import com.mzherdev.restchooser.util.ValidationUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import com.mzherdev.restchooser.util.exception.VoteUpdateImpossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository repository;

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        return repository.save(vote, userId, restaurantId);
    }

    @Override
    public void update(Vote vote, int userId, int restaurantId) throws ReflectiveOperationException {
        Vote updatable = get(vote.getId());
        vote = JpaUtil.updateEntity(updatable, vote);
        if (!Optional.ofNullable(vote.getUser().getId()).orElse(0).equals(userId)) {
            throw new VoteUpdateImpossibleException("This vote does not belong to this user.");
        }
        LocalDateTime now = LocalDateTime.now();
        if(!DateTimeUtil.canChangeVote(vote.getVoteTime(), now)) {
            throw new VoteUpdateImpossibleException("Vote Update Impossible after 11 a.m.");
        }
        vote.setVoteTime(now);
        repository.update(vote, restaurantId);
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return ValidationUtil.check(repository.get(id), id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getByUser(int userId) {
        return repository.getByUser(userId);
    }

    @Override
    public List<Vote> getByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }
}
