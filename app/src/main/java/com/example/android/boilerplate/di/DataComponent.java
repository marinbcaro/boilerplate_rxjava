package com.example.android.boilerplate.di;

import com.example.android.boilerplate.BoilerplateApplication;
import com.example.android.boilerplate.MainActivity;
import com.example.android.boilerplate.network.Repository;
import com.example.android.boilerplate.viewmodels.GitHubViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carolinamarin on 2/3/18.
 */
@Singleton
@Component(modules={DataModule.class})

public interface DataComponent {

    void inject(BoilerplateApplication retroRecyclerApplication);

    void inject(Repository repository);

    void inject(MainActivity mainActivity);

    void inject(GitHubViewModel viewModel);
}