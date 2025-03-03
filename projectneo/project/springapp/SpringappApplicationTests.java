

public class SpringappApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void test_Controller_UserController_File() {
        String filePath = "src/main/java/com/examly/springapp/controller/UserController.java";
        assertTrue(new File(filePath).exists() && new File(filePath).isFile());
    }

    @Test
    @Order(2)
    public void test_Controller_IncomeController_File() {
        String filePath = "src/main/java/com/examly/springapp/controller/IncomeController.java";
        assertTrue(new File(filePath).exists() && new File(filePath).isFile());
    }

    @Test
    @Order(3)
    public void test_Controller_BudgetController_File() {
        String filePath = "src/main/java/com/examly/springapp/controller/BudgetController.java";
        assertTrue(new File(filePath).exists() && new File(filePath).isFile());
    }

    @Test
    @Order(4)
    public void test_Controller_ExpenseController_File() {
        String filePath = "src/main/java/com/examly/springapp/controller/ExpenseController.java";
        assertTrue(new File(filePath).exists() && new File(filePath).isFile());
    }

    // Test cases for User endpoints
    @Test
    @Order(5)
    public void test_RegisterUser() {
        User user = new User("jane.doe@example.com", "Jane Doe", "1234567890");
        ResponseEntity<User> response = restTemplate.postForEntity("/api/users/register", user, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(6)
    public void test_GetUserById() {
        User user = new User("john.doe@example.com", "John Doe", "0987654321");
        ResponseEntity<User> createdUser = restTemplate.postForEntity("/api/users/register", user, User.class);

        Long userId = createdUser.getBody().getId();
        ResponseEntity<User> response = restTemplate.getForEntity("/api/users/" + userId, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test cases for Income endpoints
    @Test
    @Order(7)
    public void test_AddIncome() {
        Income income = new Income(1L, BigDecimal.valueOf(5000), "Salary", new Date());
        ResponseEntity<Income> response = restTemplate.postForEntity("/api/incomes", income, Income.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(8)
    public void test_GetAllIncomes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/incomes", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Test cases for Budget endpoints
    @Test
    @Order(9)
    public void test_CreateBudget() {
        Budget budget = new Budget(1L, BigDecimal.valueOf(2000), "Monthly Budget");
        ResponseEntity<Budget> response = restTemplate.postForEntity("/api/budgets", budget, Budget.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(10)
    public void test_GetBudgetById() {
        Budget budget = new Budget(1L, BigDecimal.valueOf(3000), "Quarterly Budget");
        ResponseEntity<Budget> createdBudget = restTemplate.postForEntity("/api/budgets", budget, Budget.class);

        Long budgetId = createdBudget.getBody().getId();
        ResponseEntity<Budget> response = restTemplate.getForEntity("/api/budgets/" + budgetId, Budget.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test cases for Expense endpoints
    @Test
    @Order(11)
    public void test_AddExpense() {
        Expense expense = new Expense(1L, BigDecimal.valueOf(500), "Groceries", new Date());
        ResponseEntity<Expense> response = restTemplate.postForEntity("/api/expenses", expense, Expense.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(12)
    public void test_GetAllExpenses() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/expenses", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}