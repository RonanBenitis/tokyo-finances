import { ref } from 'vue'
import { transferenciaService } from '@/services/tokyoFinancesService'
export function useExtrato() {
  const extrato = ref([])
  const carregando = ref(false)
  const erro = ref(null)
  async function buscarExtrato() {
    carregando.value = true
    erro.value = null
    try {
      const { data } = await transferenciaService.listarExtrato()
      extrato.value = data
    } catch (e) {
      erro.value = e.message
    } finally {
      carregando.value = false
    }
  }
  return { extrato, carregando, erro, buscarExtrato }
}
