package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Followee;
import com.agilie.dribbblesdk.domain.Follower;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.Team;
import com.agilie.dribbblesdk.domain.User;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DribbbleUsersServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2";

    //    private static final long TEST_USER_ID = 793483;
    private static final long[] TEST_USERS_ID = {107759, 41719, 3518, 108482, 22069};
    private static final long TEST_FOLLOWED_USER_ID = 400583;

    private DribbbleUserService authorizedDribbbleService;
    private DribbbleUserService dribbbleService;

    private Retrofit retrofit;

    public DribbbleUsersServiceTest() {
        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(AUTH_TOKEN_FOR_TEST);
        Retrofit retrofit = DribbbleWebServiceHelper.getRetrofitBuilder(okHttpClientBuilder).build();

        authorizedDribbbleService = DribbbleWebServiceHelper.getDribbbleUserService(retrofit);
        dribbbleService = DribbbleWebServiceHelper.getDribbbleUserService(retrofit);
    }


    public void testGetSingleUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getSingleUser(TEST_USERS_ID[4])
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                assertTrue("getSingleUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchAuthorizedUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                authorizedDribbbleService.fetchAuthenticatedUser()
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                assertTrue("fetchAuthorizaedUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Buckets */

    public void testGetUsersBuckets() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersBuckets(TEST_USERS_ID[0])
                        .enqueue(new Callback<List<Bucket>>() {
                            @Override
                            public void onResponse(Call<List<Bucket>> call, Response<List<Bucket>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Bucket>> call, Throwable t) {
                                assertTrue("getUsersBuckets is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthorizedUsersBuckets() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersBuckets()
                        .enqueue(new Callback<List<Bucket>>() {
                            @Override
                            public void onResponse(Call<List<Bucket>> call, Response<List<Bucket>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Bucket>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersBuckets is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Followers */

    public void testGetUsersFollowers() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersFollowers(TEST_USERS_ID[1], 1, 1)
                        .enqueue(new Callback<List<Follower>>() {
                            @Override
                            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Follower>> call, Throwable t) {
                                assertTrue("getUsersFollowers is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthorizedUsersFollowers() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersFollowers(1, 1)
                        .enqueue(new Callback<List<Follower>>() {
                            @Override
                            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Follower>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersFollowers is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }


    public void testGetCheckFollowingByUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                dribbbleService.getFollowingByUser(TEST_USERS_ID[3])
                        .enqueue(new Callback<List<Followee>>() {
                            @Override
                            public void onResponse(Call<List<Followee>> call, Response<List<Followee>> response) {
                                assertNotNull(response.body());
                                long followeeId = response.body().get(0).getUser().getId();
                                dribbbleService.checkUserIsFollowingAnother(TEST_USERS_ID[3], followeeId)
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                signal.countDown();
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                assertTrue("checkUserIsFollowingAnother is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<List<Followee>> call, Throwable t) {
                                assertTrue("getFollowingByUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });
        signal.await();
    }

    public void testGetCheckFollowingByCurrentUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getFollowingByCurrentUser()
                        .enqueue(new Callback<List<Followee>>() {
                            @Override
                            public void onResponse(Call<List<Followee>> call, Response<List<Followee>> response) {
                                assertNotNull(response.body());
                                long followeeId = response.body().get(0).getUser().getId();
                                authorizedDribbbleService.checkUserIsFollowed(followeeId)
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                signal.countDown();
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                assertTrue("checkUserIsFollowed is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<List<Followee>> call, Throwable t) {
                                assertTrue("getFollowingByCurrentUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testShotsForUserFollowedByUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.shotsForUserFollowedByUser()
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("shotsForUserFollowedByUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFollowUnFollowUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.unfollowUser(TEST_FOLLOWED_USER_ID)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                authorizedDribbbleService.followUser(TEST_FOLLOWED_USER_ID)
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                signal.countDown();
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                assertTrue("followUser is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                assertTrue("unfollowUser is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Likes */

    public void testGetUsersLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersLikes(TEST_USERS_ID[0])
                        .enqueue(new Callback<List<Like>>() {
                            @Override
                            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Like>> call, Throwable t) {
                                assertTrue("getUsersLikes is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersLikes()
                        .enqueue(new Callback<List<Like>>() {
                            @Override
                            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Like>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersLikes is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Projects */

    public void testGetUsersProjects() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersProjects(TEST_USERS_ID[0])
                        .enqueue(new Callback<List<Project>>() {
                            @Override
                            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Project>> call, Throwable t) {
                                assertTrue("getUsersProjects is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersProjects() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersProjects()
                        .enqueue(new Callback<List<Project>>() {
                            @Override
                            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Project>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersProjects is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Shots */

    public void testGetUsersShots() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersShots(TEST_USERS_ID[0])
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("getUsersShots is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersShots() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersShots(1, 1)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersShots is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* USER Teams */

    public void testGetUsersTeams() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersTeams(TEST_USERS_ID[0])
                        .enqueue(new Callback<List<Team>>() {
                            @Override
                            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Team>> call, Throwable t) {
                                assertTrue("getUsersTeams is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersTeams() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersTeams(1)
                        .enqueue(new Callback<List<Team>>() {
                            @Override
                            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Team>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersTeams is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

}
