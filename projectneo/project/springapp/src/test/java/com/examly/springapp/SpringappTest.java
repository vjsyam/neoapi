// package com.examly.springapp;

// import com.examly.springapp.controllers.UserController;
// import com.examly.springapp.controllers.IncomeController;
// import com.examly.springapp.controllers.ExpenseController;

// import com.examly.springapp.controllers.BudgetController;
// import com.examly.springapp.entities.User;
// import com.examly.springapp.repositories.BudgetRepository;
// import com.examly.springapp.repositories.ExpenseRepository;
// import com.examly.springapp.repositories.IncomeRepository;
// import com.examly.springapp.repositories.UserRepository;
// import com.examly.springapp.entities.Income;
// import com.examly.springapp.entities.Expense;
// import com.examly.springapp.entities.Budget;
// import com.examly.springapp.services.UserService;
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.data.domain.Sort;

// import java.lang.reflect.Field;
// import java.lang.reflect.Method;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// import lombok.Value;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Aspect;

// import com.examly.springapp.services.IncomeService;
// import com.examly.springapp.services.ExpenseService;
// import com.examly.springapp.services.BudgetService;

// import org.aspectj.lang.JoinPoint;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.test.web.server.LocalServerPort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.io.File;
// import java.time.LocalDate;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.transaction.Transactional;
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class SpringappTest {

//     @InjectMocks
//     private UserController userController;

//     @Mock
//     private UserService userService;

//     @InjectMocks
//     private IncomeController incomeController;

//     @Mock
//     private IncomeService incomeService;

//     @InjectMocks
//     private ExpenseController expenseController;

//     @Mock
//     private ExpenseService expenseService;

//     @InjectMocks
//     private BudgetController budgetController;

//     @Mock
//     private BudgetService budgetService;

//     @Autowired
//     private TestRestTemplate restTemplate;

//     private MockMvc mockMvc;

//     private User user;
//     private Income income;
//     private Expense expense;
//     private Budget budget;
//     private static final String LOG_FOLDER_PATH = "logs";
//     private static final String LOG_FILE_PATH = "logs/application.log"; 
//     private final ObjectMapper objectMapper = new ObjectMapper();
//     @BeforeEach
//     public void setUp() {
//         // Initialize mocks before each test
//         MockitoAnnotations.openMocks(this);

//         // Create mock user, income, expense, and budget
//         user = new User(null, "Jane Doe", "jane.doe@example.com", "123456", "USD", "123-456-7890", "profilePicUrl", null, null);
//         income = new Income(null, "Salary", 5000.0, LocalDate.now(), "Job", user);
//         expense = new Expense(null, 1000.0, LocalDate.now(), "Food", "Lunch at restaurant", "Credit Card", user);
//         budget = new Budget(null, "Food", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(1), "Active", user);

//         // Initialize MockMvc
//         mockMvc = MockMvcBuilders.standaloneSetup(userController, incomeController, expenseController, budgetController).build();

        
//     }
//     @Order(35)
//     @Test
//     public void Log_testLogFolderAndFileCreation() {
//         // Check if the "logs" folder exists
//         File logFolder = new File(LOG_FOLDER_PATH);
//         assertTrue(logFolder.exists(), "Log folder should be created");

//         // Check if the "application.log" file exists inside the "logs" folder
//         File logFile = new File(LOG_FILE_PATH);
//         assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
//     }
//     // User CRUD Tests
//     @Order(6)
//     @Test
//     public void CRUD_testCreateUser() throws Exception {
//         when(userService.registerUser(any(User.class))).thenReturn(user);

//         mockMvc.perform(post("/users")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"password\":\"123456\",\"preferredCurrency\":\"USD\",\"contactInfo\":\"123-456-7890\",\"profilePicture\":\"profilePicUrl\"}")
//         )
//         .andExpect(status().isCreated())
//         .andExpect(jsonPath("$.name").value("Jane Doe"))
//         .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
//     }
//     @Order(7)
//     @Test
//     public void CRUD_testGetAllUsers() throws Exception {
//         when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

//         mockMvc.perform(get("/users"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].name").value("Jane Doe"))
//                 .andExpect(jsonPath("$[0].email").value("jane.doe@example.com"));
//     }

//     // @Test
//     // public void testGetUserById() throws Exception {
//     //     when(userService.getUserById(1L)).thenReturn(Optional.of(user));

//     //     mockMvc.perform(get("/users/1"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(jsonPath("$.name").value("Jane Doe"))
//     //             .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
//     // }
//     @Order(7)
//     @Test
//     public void CRUD_testUpdateUser() throws Exception {
//         User updatedUser = new User(1L, "Jane Doe", "jane.doe@example.com", "newpassword", "USD", "123-456-7890", "newProfilePicUrl", null, null);
//         when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

//         mockMvc.perform(put("/users/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"password\":\"newpassword\",\"preferredCurrency\":\"USD\",\"contactInfo\":\"123-456-7890\",\"profilePicture\":\"newProfilePicUrl\"}")
//         )
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.password").value("newpassword"));
//     }
//     @Order(8)
//     @Test
//     public void CRUD_testDeleteUser() throws Exception {
//         doNothing().when(userService).deleteUser(1L);

//         mockMvc.perform(delete("/users/1"))
//                 .andExpect(status().isNoContent());
//     }

//     // Income CRUD Tests
//     @Order(9)
//     @Test
//     public void CRUD_testCreateIncome() throws Exception {
//         when(incomeService.saveIncome(any(Income.class))).thenReturn(income);

//         mockMvc.perform(post("/incomes")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content("{\"source\":\"Salary\",\"amount\":5000.0,\"date\":\"2024-12-26\",\"category\":\"Salary\",\"user\":{\"id\":1}}")
//     )
//     .andExpect(status().isCreated())
//     .andExpect(jsonPath("$.source").value("Salary"))  // Match the updated value
//     .andExpect(jsonPath("$.amount").value(5000.0));
//     }

//     // @Test
//     // public void testGetAllIncomes() throws Exception {
//     //     when(incomeService.getAllIncomes()).thenReturn(Arrays.asList(income));

//     //     mockMvc.perform(get("/incomes"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(jsonPath("$[0].source").value("Job"))
//     //             .andExpect(jsonPath("$[0].amount").value(5000.0));
//     // }
//    @Order(11)
//     @Test
//     public void CRUD_testGetIncomeById() throws Exception {
//         when(incomeService.getIncomeById(1L)).thenReturn(Optional.of(income));

//         mockMvc.perform(get("/incomes/1"))
//     .andExpect(status().isOk())
//     .andExpect(jsonPath("$.source").value("Salary"))  // Match the updated value
//     .andExpect(jsonPath("$.amount").value(5000.0));
//     }
//    @Order(10)
//     @Test
//     public void CRUD_testUpdateIncome() throws Exception {
//         Income updatedIncome = new Income(1L, "Freelance", 3000.0, LocalDate.now(), "Freelance", user);
//         when(incomeService.updateIncome(eq(1L), any(Income.class))).thenReturn(updatedIncome);

//         mockMvc.perform(put("/incomes/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"source\":\"Freelance\",\"amount\":3000.0,\"date\":\"2024-12-26\",\"category\":\"Freelance\",\"user\":{\"id\":1}}")
//         )
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.source").value("Freelance"))
//         .andExpect(jsonPath("$.amount").value(3000.0));
//     }
// @Order(12)
//     @Test
//     public void CRUD_testDeleteIncome() throws Exception {
//         doNothing().when(incomeService).deleteIncome(1L);

//         mockMvc.perform(delete("/incomes/1"))
//                 .andExpect(status().isNoContent());
//     }

//     // Expense CRUD Tests
//     @Order(13)
//     @Test
//     public void CRUD_testCreateExpense() throws Exception {
//         when(expenseService.saveExpense(any(Expense.class))).thenReturn(expense);

//         mockMvc.perform(post("/expenses")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"amount\":1000.0,\"date\":\"2024-12-26\",\"category\":\"Food\",\"description\":\"Lunch at restaurant\",\"paymentMode\":\"Credit Card\",\"user\":{\"id\":1}}")
//         )
//         .andExpect(status().isCreated())
//         .andExpect(jsonPath("$.amount").value(1000.0))
//         .andExpect(jsonPath("$.category").value("Food"));
//     }
//     @Order(14)
//     @Test
//     public void CRUD_testGetAllExpenses() throws Exception {
//         when(expenseService.getAllExpenses()).thenReturn(Arrays.asList(expense));

//         mockMvc.perform(get("/expenses"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].category").value("Food"))
//                 .andExpect(jsonPath("$[0].amount").value(1000.0));
//     }
//    @Order(15)
//     @Test
//     public void CRUD_testGetExpenseById() throws Exception {
//         when(expenseService.getExpenseById(1L)).thenReturn(Optional.of(expense));

//         mockMvc.perform(get("/expenses/1"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.category").value("Food"))
//                 .andExpect(jsonPath("$.amount").value(1000.0));
//     }

//  @Order(16)
//     @Test
//     public void CRUD_testUpdateExpense() throws Exception {
//         Expense updatedExpense = new Expense(1L, 1500.0, LocalDate.now(), "Entertainment", "Movie ticket", "Debit Card", user);
//         when(expenseService.updateExpense(eq(1L), any(Expense.class))).thenReturn(updatedExpense);

//         mockMvc.perform(put("/expenses/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"amount\":1500.0,\"date\":\"2024-12-26\",\"category\":\"Entertainment\",\"description\":\"Movie ticket\",\"paymentMode\":\"Debit Card\",\"user\":{\"id\":1}}")
//         )
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.category").value("Entertainment"))
//         .andExpect(jsonPath("$.amount").value(1500.0));
//     }
// @Order(17)
//     @Test
//     public void CRUD_testDeleteExpense() throws Exception {
//         doNothing().when(expenseService).deleteExpense(1L);

//         mockMvc.perform(delete("/expenses/1"))
//                 .andExpect(status().isNoContent());
//     }

//     // Budget CRUD Tests
//     @Order(18)
//     @Test
//     public void CRUD_testCreateBudget() throws Exception {
//         when(budgetService.saveBudget(any(Budget.class))).thenReturn(budget);

//         mockMvc.perform(post("/budgets")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"category\":\"Food\",\"allocatedAmount\":2000.0,\"startDate\":\"2024-12-26\",\"endDate\":\"2025-01-26\",\"status\":\"Active\"}")
//         )
//         .andExpect(status().isCreated())
//         .andExpect(jsonPath("$.category").value("Food"))
//         .andExpect(jsonPath("$.allocatedAmount").value(2000.0))
//         .andExpect(jsonPath("$.status").value("Active"));
//     }
//    @Order(19)
//     @Test
//     public void CRUD_testGetAllBudgets() throws Exception {
//         when(budgetService.getAllBudgets()).thenReturn(Arrays.asList(budget));

//         mockMvc.perform(get("/budgets"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].category").value("Food"))
//                 .andExpect(jsonPath("$[0].allocatedAmount").value(2000.0))
//                 .andExpect(jsonPath("$[0].status").value("Active"));
//     }
//   @Order(20)
//     @Test
//     public void CRUD_testGetBudgetById() throws Exception {
//         when(budgetService.getBudgetById(1L)).thenReturn(Optional.of(budget));

//         mockMvc.perform(get("/budgets/1"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.category").value("Food"))
//                 .andExpect(jsonPath("$.allocatedAmount").value(2000.0))
//                 .andExpect(jsonPath("$.status").value("Active"));
//     }
// @Order(21)
//     @Test
//     public void CRUD_testGetBudgetsByUserIdAndCategory() throws Exception {
//         when(budgetService.getBudgetsByUserIdAndCategory(1L, "Food")).thenReturn(Arrays.asList(budget));

//         mockMvc.perform(get("/budgets/filter")
//     .param("userId", "1")
//     .param("category", "Food"))
//     .andExpect(status().isOk())
//     .andExpect(jsonPath("$").isArray());  // Expecting an array of budgets
//     }
// @Order(22)
//     @Test
//     public void CRUD_testUpdateBudget() throws Exception {
//         Budget updatedBudget = new Budget(1L, "Food", 2500.0, LocalDate.now(), LocalDate.now().plusMonths(2), "Active", user);
//         when(budgetService.updateBudget(eq(1L), any(Budget.class))).thenReturn(updatedBudget);

//         mockMvc.perform(put("/budgets/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"category\":\"Food\",\"allocatedAmount\":2500.0,\"startDate\":\"2024-12-26\",\"endDate\":\"2025-02-26\",\"status\":\"Active\"}")
//         )
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.allocatedAmount").value(2500.0));
//     }
// @Order(23)
//     @Test
//     public void CRUD_testDeleteBudget() throws Exception {
//         doNothing().when(budgetService).deleteBudget(1L);

//         mockMvc.perform(delete("/budgets/1"))
//                 .andExpect(status().isNoContent());
//     }

//     // @Test
//     // public void Entity_testBudgetEntityExists() {
//     //     Budget budget = new Budget();
//     //     // Just check if Budget object is created
//     //     if (budget != null) {
//     //         System.out.println("Budget Entity Created: " + budget);
//     //     }
//     // }
//     // // Test for User Entity
//     // @Test
//     // public void Entity_testUserEntityExists() {
//     //     User user = new User();
//     //     // Just check if User object is created
//     //     if (user != null) {
//     //         System.out.println("User Entity Created: " + user);
//     //     }
//     // }

//     // // Test for Income Entity
//     // @Test
//     // public void Entity_testIncomeEntityExists() {
//     //     Income income = new Income();
//     //     // Just check if Income object is created
//     //     if (income != null) {
//     //         System.out.println("Income Entity Created: " + income);
//     //     }
//     // }

//     // // Test for Expense Entity
//     // @Test
//     // public void Entity_testExpenseEntityExists() {
//     //     Expense expense = new Expense();
//     //     // Just check if Expense object is created
//     //     if (expense != null) {
//     //         System.out.println("Expense Entity Created: " + expense);
//     //     }
//     // }

//     // // Test for Budget Controller
    

//     // @Test
//     // public void Controller_testBudgetControllerExists() {
//     //     // Check if the controller is created and print
//     //     if (budgetController != null) {
//     //         System.out.println("Budget Controller Created: " + budgetController);
//     //     }
//     // }

//     // // Test for User Controller
    
//     // @Test
//     // public void Controller_testUserControllerExists() {
//     //     // Check if the controller is created and print
//     //     if (userController != null) {
//     //         System.out.println("User Controller Created: " + userController);
//     //     }
//     // }

//     // // Test for Income Controller
    

//     // @Test
//     // public void Controller_testIncomeControllerExists() {
//     //     // Check if the controller is created and print
//     //     if (incomeController != null) {
//     //         System.out.println("Income Controller Created: " + incomeController);
//     //     }
//     // }

//     // // Test for Expense Controller
    

//     // @Test
//     // public void Controller_testExpenseControllerExists() {
//     //     // Check if the controller is created and print
//     //     if (expenseController != null) {
//     //         System.out.println("Expense Controller Created: " + expenseController);
//     //     }
//     // }

//     // // Test for Budget Service
    

//     // @Test
//     // public void Service_testBudgetServiceExists() {
//     //     // Check if the service is created and print
//     //     if (budgetService != null) {
//     //         System.out.println("Budget Service Created: " + budgetService);
//     //     }
//     // }

//     // // Test for User Service
    

//     // @Test
//     // public void Service_testUserServiceExists() {
//     //     // Check if the service is created and print
//     //     if (userService != null) {
//     //         System.out.println("User Service Created: " + userService);
//     //     }
//     // }

//     // // Test for Income Service
   

//     // @Test
//     // public void Service_testIncomeServiceExists() {
//     //     // Check if the service is created and print
//     //     if (incomeService != null) {
//     //         System.out.println("Income Service Created: " + incomeService);
//     //     }
//     // }

//     // // Test for Expense Service
   
//     // @Test
//     // public void Service_testExpenseServiceExists() {
//     //     // Check if the service is created and print
//     //     if (expenseService != null) {
//     //         System.out.println("Expense Service Created: " + expenseService);
//     //     }
//     // }

//     // Test for Budget Repository
//     @Autowired
//     private BudgetRepository budgetRepository;

//     @Order(3)
//     @Test
//     public void Repository_testBudgetRepositoryExists() {
//         // Check if the repository is created and print
//         if (budgetRepository != null) {
//             System.out.println("Budget Repository Created: " + budgetRepository);
//         }
//     }

//     // Test for User Repository
//     @Autowired
//     private UserRepository userRepository;

//     @Order(4)
//     @Test
//     public void Repository_testUserRepositoryExists() {
//         // Check if the repository is created and print
//         if (userRepository != null) {
//             System.out.println("User Repository Created: " + userRepository);
//         }
//     }

//     // Test for Income Repository
//     @Autowired
//     private IncomeRepository incomeRepository;

//     @Order(5)
//     @Test
//     public void Repository_testIncomeRepositoryExists() {
//         // Check if the repository is created and print
//         if (incomeRepository != null) {
//             System.out.println("Income Repository Created: " + incomeRepository);
//         }
//     }
    
// private void checkAnnotationExists(String className, String annotationName) {
// 		try {
// 			Class<?> clazz = Class.forName(className);
// 			ClassLoader classLoader = clazz.getClassLoader();
// 			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
// 			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
// 		} catch (ClassNotFoundException | NullPointerException e) {
// 			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
// 		}
// 	}

//     // Test for Expense Repository
//     @Autowired
//     private ExpenseRepository expenseRepository;
// @Order(24)
//     @Test
//     public void CRUD_testExpenseRepositoryExists() {
//         // Check if the repository is created and print
//         if (expenseRepository != null) {
//             System.out.println("Expense Repository Created: " + expenseRepository);
//         }
//     }
// @Order(27)
//     @Test
// void PaginateSorting_testPagination() {
//     // Simulate a GET request to fetch users with pagination
//     ResponseEntity<String> response = restTemplate.getForEntity("/users?page=0&size=10", String.class);
    
//     // Assert that the response status is OK
//     assertEquals(HttpStatus.OK, response.getStatusCode(), "Response status should be OK");

//     // Assert that the response body is not empty (assuming it's a JSON array of users)
//     assertNotNull(response.getBody(), "Response body should not be null");

    
// }
// @Order(25)
// @Test
//     public void JPQL_testFindUsersByPreferredCurrency() {
//         // Given: Create users with different preferred currencies and save them
//         User user1 = new User();
//         user1.setName("John");
//         user1.setPreferredCurrency("USD");
//         userRepository.save(user1);

//         User user2 = new User();
//         user2.setName("Jane");
//         user2.setPreferredCurrency("EUR");
//         userRepository.save(user2);

//         User user3 = new User();
//         user3.setName("Doe");
//         user3.setPreferredCurrency("USD");
//         userRepository.save(user3);

//         // When: Run the JPQL query to find users with preferred currency "USD"
//         List<User> users = userRepository.findUsersByPreferredCurrency("USD");

//         // Then: Verify that the correct users are returned
//         assertNotNull(users);
//         //assertEquals(2, users.size(), "There should be 2 users with preferred currency 'USD'");
//         assertEquals("John", users.get(0).getName(), "First user should be John");
//         assertEquals("Doe", users.get(1).getName(), "Second user should be Doe");
//     }
//    @Order(26)
//     @Test
// @Transactional
// public void JPQL_testFindBudgetsByCategory() {
//     // Given: Create and save a user
//     User user = new User();
//     user.setName("Jane Doe");
//     user.setEmail("jane.doe@example.com");
//     user.setPreferredCurrency("USD");
//     userRepository.save(user); // Save the user to ensure it is persisted

//     // Create and save budgets
//     Budget budget1 = new Budget(null, "Food", 2000.0, LocalDate.now(), LocalDate.now().plusMonths(1), "Active", user);
//     Budget budget2 = new Budget(null, "Travel", 3000.0, LocalDate.now(), LocalDate.now().plusMonths(1), "Active", user);
//     budgetRepository.save(budget1); // Save the first budget
//     budgetRepository.save(budget2); // Save the second budget

//     // When: Run the JPQL query to find budgets by category "Food"
//     List<Budget> budgets = budgetRepository.findBudgetsByCategory("Food");

//     // Then: Verify the results
//     assertNotNull(budgets, "The result list should not be null");
//     assertEquals(1, budgets.size(), "There should be 1 budget with category 'Food'");
//     assertEquals("Food", budgets.get(0).getCategory(), "The budget category should be 'Food'");
//     assertEquals(user.getName(), budgets.get(0).getUser().getName(), "The budget user should be Jane Doe");
// }
// @Order(31)
//     @Test 
//     public void Swagger_testConfigurationFolder() { 
//         String directoryPath = "src/main/java/com/examly/springapp/configuration"; // Replace with the path to your directory 
//         File directory = new File(directoryPath); 
//         assertTrue(directory.exists() && directory.isDirectory()); 
//     }
//     @Order(32)
//     @Test

// 	public void Swagger_testConfigFile() {

// 		String filePath = "src/main/java/com/examly/springapp/configuration/SwaggerConfig.java";

// 		// Replace with the path to your file

// 		File file = new File(filePath);

// 		assertTrue(file.exists() && file.isFile());

// 	}
//     @Order(33)
//     @Test
// 	   public void Swagger_testConfigHasAnnotation() {
// 	       checkAnnotationExists("com.examly.springapp.configuration.SwaggerConfig", "org.springframework.context.annotation.Configuration");
// 	   }
// 	 @Order(34)
// 	@Test
//     void Swagger_testSwaggerUIEndpointIsAccessible() {
//         // Make a GET request to the Swagger UI URL
//         ResponseEntity<String> response = restTemplate.getForEntity("/swagger-ui/index.html", String.class);

//         // Assert that the endpoint returns a 200 OK status
//         assertEquals(HttpStatus.OK, response.getStatusCode(), "Swagger UI endpoint is not accessible");
//     }
//     @Order(29)
//     @Test
//     public void Mapping_testOneToManyAnnotationPresent() throws NoSuchFieldException {
//         // Get the fields of the User class
//         Field incomesField = User.class.getDeclaredField("incomes");
//         Field expensesField = User.class.getDeclaredField("expenses");

//         // Assert that @OneToMany annotation is present
//         Assertions.assertThat(incomesField.isAnnotationPresent(OneToMany.class))
//                   .as("Field 'incomes' should have @OneToMany annotation")
//                   .isTrue();

//         Assertions.assertThat(expensesField.isAnnotationPresent(OneToMany.class))
//                   .as("Field 'expenses' should have @OneToMany annotation")
//                   .isTrue();
//     }
//    @Order(30)
//     @Test
//     public void Mapping_testManyToOneAnnotationPresent() throws NoSuchFieldException {
//         // Access the 'user' field in the Income class
//         Field userField = Income.class.getDeclaredField("user");

//         // Check if the @ManyToOne annotation is present
//         assertTrue(userField.isAnnotationPresent(ManyToOne.class), 
//                    "Field 'user' should have @ManyToOne annotation");
//     }
//     @Order(36)
//     @Test
//     void AOP_testAopFunctionality() {
//         // Trigger AOP by making a GET request to UserController
//         ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);

//         // Assert that the response is valid (indicating the controller method was executed)
//         assertNotNull(response.getBody());

//         // You can check the console log for the "AOP: Method called" message
//     }
    
//     //  @Test
//     // public void Annotation_testJsonPropertyAnnotationPresence() throws NoSuchFieldException {
//     //     // Check @JsonProperty on 'source'
//     //     // Field sourceField = Income.class.getDeclaredField("source");
//     //     // assertTrue(sourceField.isAnnotationPresent(JsonProperty.class),
//     //     //         "The 'source' field should be annotated with @JsonProperty");

//     //     // // Check @JsonProperty on 'amount'
//     //     // Field amountField = Income.class.getDeclaredField("amount");
//     //     // assertTrue(amountField.isAnnotationPresent(JsonProperty.class),
//     //     //         "The 'amount' field should be annotated with @JsonProperty");

//     //     // // Check @JsonProperty on 'category'
//     //     // Field categoryField = Income.class.getDeclaredField("category");
//     //     // assertTrue(categoryField.isAnnotationPresent(JsonProperty.class),
//     //     //         "The 'category' field should be annotated with @JsonProperty");
//     // }

//     // @Test
//     // public void testJsonIgnoreAnnotationPresence() throws NoSuchFieldException {
//     //     // Check @JsonIgnore on 'date'
//     //     Field dateField = Income.class.getDeclaredField("date");
//     //     assertTrue(dateField.isAnnotationPresent(JsonIgnore.class),
//     //             "The 'date' field should be annotated with @JsonIgnore");
//     // }
//     @Order(1)
//     @Test
// 	void Annotation_testUserHasJSONIgnoreAnnotations() throws Exception {
// 		// Path to the Book entity file
// 		Path entityFilePath = Paths.get("src/main/java/com/examly/springapp/entities/User.java");

// 		// Read the content of the entity file as a string
// 		String entityFileContent = Files.readString(entityFilePath);

		
// 		assertTrue(entityFileContent.contains("@JsonIgnore"), "User entity should contain @JsonIgnore annotation");
// 	}
//     @Order(2)
//     @Test
//     void Annotation_testJoinColumnAnnotationPresent() throws NoSuchFieldException {
//         // Get the class of the entity
//         Class<?> clazz = Income.class; // Replace with your entity class name

//         // Get the field you want to check
//         Field field = clazz.getDeclaredField("user"); // Replace with the field name where @JoinColumn is used

//         // Check if the @JoinColumn annotation is present
//         boolean isAnnotationPresent = field.isAnnotationPresent(JoinColumn.class);

//         // Assert that @JoinColumn is present
//         assertTrue(isAnnotationPresent, "@JoinColumn annotation should be present on the 'user' field");
//     }
// @Order(28)
//     @Test
// void PaginateSorting_testGetAllIncomesSorted() {
//     // Mock data
//     Income income1 = new Income(1L, "Salary", 500.0, LocalDate.of(2025, 1, 1), "Job", null);
//     Income income2 = new Income(2L, "Bonus", 1000.0, LocalDate.of(2025, 1, 2), "Job", null);

//     // Mock service behavior
//     List<Income> mockList = Arrays.asList(income1, income2);
//     when(incomeService.getAllIncomesSorted("amount")).thenReturn(mockList);

//     // Call the controller method
//     ResponseEntity<List<Income>> response = incomeController.getAllIncomesSorted("amount");

//     // Verify results
//     assertNotNull(response.getBody(), "Response body should not be null");
//     assertEquals(2, response.getBody().size(), "Expected 2 incomes in the sorted list");
//     assertEquals(500.0, response.getBody().get(0).getAmount(), "First income amount should be 500.0");
//     assertEquals(1000.0, response.getBody().get(1).getAmount(), "Second income amount should be 1000.0");
// }
// }
