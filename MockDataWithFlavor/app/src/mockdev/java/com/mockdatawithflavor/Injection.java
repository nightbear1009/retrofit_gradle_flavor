package com.mockdatawithflavor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Path;
import retrofit.mock.CallBehaviorAdapter;
import retrofit.mock.MockRetrofit;
import retrofit.mock.NetworkBehavior;
import retrofit.mock.RxJavaBehaviorAdapter;
import rx.Observable;

/**
 * Created by tedliang on 2015/12/3.
 */
public class Injection {
    public static SimpleService.GitHub getInjection() {

        // Create the Behavior object which manages the fake behavior and the background executor.
        NetworkBehavior behavior = NetworkBehavior.create();
//        behavior.setDelay(3000, TimeUnit.MILLISECONDS);
        // Create the mock implementation and use MockRetrofit to apply the behavior to it.
        NetworkBehavior.Adapter<?> adapter = RxJavaBehaviorAdapter.create();
        MockRetrofit mockRetrofit = new MockRetrofit(behavior, adapter);
        MockGitHub mockGitHub = new MockGitHub();
        SimpleService.GitHub gitHub = mockRetrofit.create(SimpleService.GitHub.class, mockGitHub);
        return gitHub;
    }

    public static class MockGitHub implements SimpleService.GitHub{
        List<SimpleService.Contributor> contributors = new ArrayList<>();

        public MockGitHub() {
            // Seed some mock data.
            addContributor("square", "retrofit", "John Doe", 12);
            addContributor("square", "retrofit", "Bob Smith", 2);
            addContributor("square", "retrofit", "Big Bird", 40);
            addContributor("square", "picasso", "Proposition Joe", 39);
            addContributor("square", "picasso", "Keiser Soze", 152);
        }

        private void addContributor(String owner, String repo, String name, int contributions) {
            contributors.add(new SimpleService.Contributor(name, contributions));
        }

        @Override
        public Observable<List<SimpleService.Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo) {
            return Observable.just(contributors);
        }
    }


}
