export interface RegistrationDetails {
  name: string
  email: string
}

export interface ConfirmRegistration {
  regId: string
  timestamp: number
}
