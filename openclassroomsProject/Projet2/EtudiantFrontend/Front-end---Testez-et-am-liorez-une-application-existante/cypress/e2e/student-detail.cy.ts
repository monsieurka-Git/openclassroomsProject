describe('Student Detail', () => {
  it('should display student details', () => {
    cy.intercept('POST', '**/api/auth/login', {
      statusCode: 200,
      body: { token: 'fake-jwt-token' }
    }).as('loginRequest');

    cy.intercept('GET', '**/api/students/1', {
      statusCode: 200,
      body: { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' }
    }).as('getStudent');

    cy.visit('/login');
    cy.get('#login').type('admin');
    cy.get('#password').type('admin');
    cy.get('button[type="submit"]').click();
    cy.wait('@loginRequest');

    cy.visit('/students/1');
    cy.wait('@getStudent');

    cy.contains('Détails de l’étudiant').should('be.visible');
    cy.contains('Prénom').should('exist');
    cy.contains('John').should('exist');
    cy.contains('Nom').should('exist');
    cy.contains('Doe').should('exist');
    cy.contains('Email').should('exist');
    cy.contains('john@example.com').should('exist');
  });
});
