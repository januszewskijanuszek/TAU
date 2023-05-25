describe('password test', () => {
    beforeEach(() => {
        cy.clearCookies()
        cy.clearLocalStorage()
      })
    it('correct login', () => {
        cy.visit('https://www.saucedemo.com')
        cy.get('#user-name').type('standard_user')
        cy.get('#password').type('secret_sauce')
        cy.get('#login-button').click()
        cy.get('[class="app_logo"]').contains("Swag Labs")
    })
})