describe('Login Page', () => {
  it('should login successfully', () => {
    cy.intercept('POST', '**/api/auth/login', {
      statusCode: 200,
      body: { token: 'fake-jwt-token' }
    }).as('loginRequest');

    cy.intercept('GET', '**/api/students', {
      statusCode: 200,
      body: [
        { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
        { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' }
      ]
    }).as('getStudents');

    cy.visit('/login');
    cy.get('#login').type('admin');
    cy.get('#password').type('admin');
    cy.get('button[type="submit"]').click();

    cy.wait('@loginRequest');
    cy.url().should('include', '/students');
    cy.wait('@getStudents');
    cy.contains('Liste des étudiants').should('be.visible');
    cy.contains('John Doe').should('exist');
    cy.contains('Ali Ben').should('exist');
  });
});
