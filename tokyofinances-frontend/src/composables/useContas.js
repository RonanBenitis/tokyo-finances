import { ref } from 'vue'
import { contaService } from '@/services/tokyoFinancesService'
export function useContas() {
  const contas = ref([])
  const carregando = ref(false)
  const erro = ref(null)
  async function buscarContas() {
    carregando.value = true
    erro.value = null
    try {
      const { data } = await contaService.listarContas()
      contas.value = data
    } catch (e) {
      erro.value = e.message
    } finally {
      carregando.value = false
    }
  }
  return { contas, carregando, erro, buscarContas }
}
