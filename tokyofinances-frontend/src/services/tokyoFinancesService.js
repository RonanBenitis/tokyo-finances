import apiClient from './apiClient'
export const contaService = {
  listarContas() {
    return apiClient.get('/contas')
  },
}
export const transferenciaService = {
  listarExtrato() {
    return apiClient.get('/transferencias')
  },
  agendar(payload) {
    return apiClient.post('/transferencias', payload)
  },
}
