package com.mockdatawithflavor;

import java.util.List;
import java.util.Observable;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public final class SimpleService {
  public static final String API_URL = "https://api.github.com";

  public static class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
      this.login = login;
      this.contributions = contributions;
    }
  }

  public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    rx.Observable<List<Contributor>> contributors(
        @Path("owner") String owner,
        @Path("repo") String repo);
  }

}