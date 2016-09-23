package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.repository.VoteRepository;
import com.mzherdev.restchooser.util.exception.ExceptionUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public void update(Vote vote) {
        repository.save(vote);
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }
}
