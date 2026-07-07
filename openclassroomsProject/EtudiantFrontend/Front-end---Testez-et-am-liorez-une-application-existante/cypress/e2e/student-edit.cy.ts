describe('Edit Student', () => {
  it('should edit an existing student successfully', () => {
    cy.intercept('POST', '**/api/auth/login', {
      statusCode: 200,
      body: { token: 'fake-jwt-token' }
    }).as('loginRequest');

    cy.intercept('GET', '**/api/students/1', {
      statusCode: 200,
      body: { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' }
    }).as('getStudent');

    cy.intercept('PUT', '**/api/students/1', {
      statusCode: 200,
      body: { id: 1, firstName: 'Johnny', lastName: 'Doe', email: 'johnny@example.com' }
    }).as('updateStudent');

    cy.intercept('GET', '**/api/students', {
      statusCode: 200,
      body: [
        { id: 1, firstName: 'Johnny', lastName: 'Doe', email: 'johnny@example.com' },
        { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' }
      ]
    }).as('getStudents');

    cy.visit('/login');
    cy.get('#login').type('admin');
    cy.get('#password').type('admin');
    cy.get('button[type="submit"]').click();
    cy.wait('@loginRequest');

    cy.visit('/students/edit/1');
    cy.wait('@getStudent');

    cy.get('input[name="firstName"]').should('have.value', 'John');
    cy.get('input[name="lastName"]').should('have.value', 'Doe');
    cy.get('input[name="email"]').should('have.value', 'john@example.com');

    cy.get('input[name="firstName"]').clear().type('Johnny');
    cy.get('input[name="email"]').clear().type('johnny@example.com');

    cy.get('button[type="submit"]').click();
    cy.wait('@updateStudent');

    cy.url().should('include', '/students');
    cy.wait('@getStudents');

    cy.contains('Johnny Doe').should('exist');
  });
});
