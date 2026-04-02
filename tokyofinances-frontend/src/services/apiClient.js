import axios from 'axios'
const apiClient = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
})
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    const message =
      error.response?.data?.message ||
      error.response?.data?.erro ||
      'Erro inesperado ao comunicar com o servidor.'
    return Promise.reject(new Error(message))
  },
)
export default apiClient
