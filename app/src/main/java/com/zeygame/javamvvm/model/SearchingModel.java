package com.zeygame.javamvvm.model;

import java.util.List;

public class SearchingModel {
//    private ArrayList<MovieModel> Search;
    private List<MovieModel> Search;
    private String totalResults;
    private String Response;

    public SearchingModel(List<MovieModel> search, String totalResults, String response) {
        Search = search;
        this.totalResults = totalResults;
        Response = response;
    }

    public List<MovieModel> getSearch() {
        return Search;
    }

    public void setSearch(List<MovieModel> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
