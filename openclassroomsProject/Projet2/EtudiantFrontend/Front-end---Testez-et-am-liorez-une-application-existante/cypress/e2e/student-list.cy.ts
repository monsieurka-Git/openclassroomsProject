describe('Student List', () => {
  beforeEach(() => {
    cy.intercept('GET', '**/api/students', {
      statusCode: 200,
      body: [
        { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
        { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' }
      ]
    }).as('getStudents');
  });

  it('should display students list', () => {
    cy.visit('/students', {
      onBeforeLoad(win) {
        win.localStorage.setItem('token', 'fake-jwt-token');
      }
    });

    cy.wait('@getStudents');
    cy.contains('John Doe').should('exist');
    cy.contains('Ali Ben').should('exist');
  });
});
