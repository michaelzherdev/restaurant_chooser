package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface VoteService {

    Vote save(Vote vote);

    void update(Vote vote);

    Vote get(int id) throws NotFoundException;

    List<Vote> getAll();
}
