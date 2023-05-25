describe('site test', () => {
  beforeEach(() => {
    cy.clearCookies()
    cy.clearLocalStorage()
  })
  it('site exists good', () => {
    cy.visit('https://www.saucedemo.com')
    cy.title().should('eq', 'Swag Labs')
  })
})