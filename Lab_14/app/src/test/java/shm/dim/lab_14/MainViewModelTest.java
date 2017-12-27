package shm.dim.lab_14;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import shm.dim.lab_14.model.User;
import shm.dim.lab_14.view_model.MainViewModel;

import static org.junit.Assert.assertEquals;

public class MainViewModelTest {
    @Mock Context mockContext;
    @Mock User mockUser;

    @Before
    public void start(){
        System.out.println("Test start");
        MockitoAnnotations.initMocks(this);
    }

    @Test(timeout = 2000)
    public void test_GetUserLoginMainVM() {
        mockUser = new User("user", "password");

        MainViewModel mainViewModel = new MainViewModel(mockContext, mockUser);

        assertEquals("user", mainViewModel.getLogin());
        System.out.println("Test ok");
    }

    @Ignore
    @Test
    public void test_GetUserPasswordMainVM() {
        mockUser = new User("user", "password");

        MainViewModel mainViewModel = new MainViewModel(mockContext, mockUser);

        assertEquals("password", mainViewModel.getPassword());
        System.out.println("Test ok");
    }

    @After
    public void finish(){
        System.out.println("Test finish");
    }
}