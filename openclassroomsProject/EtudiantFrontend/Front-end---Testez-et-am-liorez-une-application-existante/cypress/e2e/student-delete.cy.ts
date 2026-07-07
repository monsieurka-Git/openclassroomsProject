describe('Delete Student', () => {
  it('should delete an existing student successfully', () => {
    cy.intercept('GET', '**/api/students', {
      statusCode: 200,
      body: [
        { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
        { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' }
      ]
    }).as('getStudents');

    cy.intercept('DELETE', '**/api/students/1', {
      statusCode: 200,
      body: {}
    }).as('deleteStudent');

    cy.visit('/students', {
      onBeforeLoad(win) {
        win.localStorage.setItem('token', 'fake-jwt-token');
      }
    });

    cy.wait('@getStudents');
    cy.contains('John Doe').should('exist');
    cy.contains('Ali Ben').should('exist');

    cy.contains('li', 'John Doe').contains('Supprimer').click();
    cy.wait('@deleteStudent');

    cy.contains('John Doe').should('not.exist');
    cy.contains('Ali Ben').should('exist');
  });
});
