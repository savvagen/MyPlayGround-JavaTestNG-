package data;


import org.testng.annotations.DataProvider;


public class LoginData {

    @DataProvider(name = "invalidEmailData")
    public Object[][]invalidEmailData(){
        return new Object[][]{
                {"genchevskiy@@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"genchevski y@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"genchevskiqqwq@singree.com", "19021992qa", "Неправильный логин или пароль"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][]invalidLoginData(){
        return new Object[][]{
                {"Главная", "genchevskiy@@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"Главная", "genchevski y@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"Главная", "genchevskiqqwq@singree.com", "19021992qa", "Неправильный логин или пароль"}
        };
    }


}
