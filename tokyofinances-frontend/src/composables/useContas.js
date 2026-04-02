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
      contas.value = Object.entries(data).map(([numero, saldo]) => ({
        numeroConta: numero,
        titular: `Cliente ${numero.substring(0, 4)}`,
        saldo: saldo,
      }))
    } catch (e) {
      erro.value = e.message
    } finally {
      carregando.value = false
    }
  }
  return { contas, carregando, erro, buscarContas }
}
