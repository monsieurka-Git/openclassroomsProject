describe('Create Student Form', () => {
  it('should create a new student', () => {
    cy.intercept('POST', '**/api/students', {
      statusCode: 201,
      body: { id: 3, firstName: 'John', lastName: 'Test', email: 'John@test.com' }
    }).as('createStudent');

    cy.intercept('GET', '**/api/students', {
      statusCode: 200,
      body: [
        { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
        { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' },
        { id: 3, firstName: 'John', lastName: 'Test', email: 'John@test.com' }
      ]
    }).as('getStudents');

    cy.visit('/students/add', {
      onBeforeLoad(win) {
        win.localStorage.setItem('token', 'fake-jwt-token');
      }
    });

    cy.get('input[name="firstName"]').type('John');
    cy.get('input[name="lastName"]').type('Test');
    cy.get('input[name="email"]').type('John@test.com');

    cy.get('button[type="submit"]').click();
    cy.wait('@createStudent');

    cy.url().should('include', '/students');
    cy.wait('@getStudents');
  });
});
