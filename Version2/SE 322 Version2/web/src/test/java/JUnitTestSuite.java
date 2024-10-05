import SE322.exceptions.*;
import SE322.model.*;
import SE322.model.shopingCartTest.ShoppingCartConstructorTest;
import SE322.model.shopingCartTest.ShoppingCartDataUpdateTest;
import SE322.service.ProductServiceImpl.*;
import SE322.web.controller.*;
import SE322.web.controller.shoppingCartControllerTest.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


// JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CategoryNotFoundExceptionTest.class, InvalidArgumentsExceptionTest.class, InvalidUserCredentialsExceptionTest.class,
        PasswordsDoNotMatchExceptionTest.class, ProductAlreadyInShoppingCartExceptionTest.class, ProductNotFoundExceptionTest.class,
        ShoppingCartNotFoundExceptionTest.class, UsernameAlreadyExistsExceptionTest.class, UserNotFoundExceptionTest.class,
        ShoppingCartConstructorTest.class, ShoppingCartDataUpdateTest.class, CategoryConstructorTest.class, ManufacturerConstructorTest.class,
        ProductConstructorTest.class, ProductDataUpdateTest.class, RoleGetAuthorityTest.class, UserGetAuthoritiesTest.class,
        // Removed: ProductServiceImplDeleteByIdTest.class, // ProductServiceImplEditTest.class, ProductServiceImplFindAllTest.class,
        // ProductServiceImplFindByIdTest.class, ProductServiceImplFindByNameTest.class, ProductServiceImplSaveTest.class,
        // AddProductToShoppingCartTest.class, FilterShoppingCartsTest.class, GetFilterShoppingCartsPageTest.class, GetShoppingCartTest.class,
        // ShowEditShoppingCartTest.class, UpdateShoppingCartTest.class, addProductPageTest.class, deleteProductTest.class,
        // editProductPageTest.class, editProductTest.class, GetHomePageTest.class, registerTest.class,
        GetLoginPageTest.class, getManufacturersPageTest.class, getProductPageTest.class, getRegisterPageTest.class,
        LogoutTest.class, saveProductTest.class
})
public class JUnitTestSuite {
}
