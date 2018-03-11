package com.example.android.boilerplate.viewmodels;

/**
 * Created by carolinamarin on 3/10/18.
 */
public class GitHubViewModelTest {
//
//    private GitHubViewModel viewModel;
//    private List<GitRepo> reposList;
//    @Mock
//    Repository repository;
//    @Mock
//    ICallback callback;
//    @Mock
//    Call<List<GitRepo>> mockCall;
//    @Mock
//    ResponseBody responseBody;
//    @Captor
//    ArgumentCaptor<Callback<List<GitRepo>>> argumentCaptor;
//
//
//
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        viewModel= new GitHubViewModel(callback, repository);
//        reposList = Collections.singletonList(new GitRepo());
//    }
//
//    @Test
//    public void GivenDataCall_Should_Get_Repos(){
//
//        when(repository.getListItems("codepath")).thenReturn(mockCall);
//        Response<List<GitRepo>> res=Response.success(reposList);
//        viewModel.getData();
//
//        verify(mockCall).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onResponse(null,res);
//        verify(callback).showResults(reposList);
//
//    }
//
//    @Test
//    public void GivenDataCall_shouldDoNothing_Bad_Request(){
//
//    }
//
//    @Test
//    public void GivenDataCall_should_Show_Error(){
//
//        when(repository.getListItems("codepath")).thenReturn(mockCall);
//        Throwable throwable= new Throwable(new RuntimeException());
//
//        viewModel.getData();
//        verify(mockCall).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onFailure(null,throwable);
//
//        verify(callback).showError();
//
//    }


}