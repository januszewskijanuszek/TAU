describe('login test', () => {
    beforeEach(() => {
        cy.clearCookies()
        cy.clearLocalStorage()
      })
    it('login-no password', () =>{
      cy.visit('https://www.saucedemo.com')
      cy.get('#user-name').type('standard_user')
      cy.get('#login-button').click()
      cy.get('[data-test="error"]').contains("Epic sadface: Password is required")
    })
    it('login wrong password', () =>{
        cy.visit('https://www.saucedemo.com')
        cy.get('#user-name').type('standard_user')
        cy.get('#password').type('standard_user')
        cy.get('#login-button').click()
        cy.get('[data-test="error"]').contains("Epic sadface: Username and password do not match any user in this service")
    })
    it('no login', () =>{
        cy.visit('https://www.saucedemo.com')
        cy.get('#login-button').click()
        cy.get('[data-test="error"]').contains("Epic sadface: Username is required")
    })
  })