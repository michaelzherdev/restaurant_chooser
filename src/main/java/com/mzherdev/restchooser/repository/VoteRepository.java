package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Vote;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface VoteRepository {

    Vote save(Vote vote);

    Vote get(int id);

    List<Vote> getAll();
}
