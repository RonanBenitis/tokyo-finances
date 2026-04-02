import { ref, computed } from 'vue'
import { transferenciaService } from '@/services/tokyoFinancesService'
export function useAgendamento() {
  const form = ref({
    contaOrigem: '',
    contaDestino: '',
    valor: '',
    dataTransferencia: '',
  })
  const enviando = ref(false)
  const sucesso = ref(null)
  const erro = ref(null)
  const dataMinima = computed(() => {
    const hoje = new Date()
    return hoje.toISOString().split('T')[0]
  })
  function validar() {
    const { contaOrigem, contaDestino, valor, dataTransferencia } = form.value
    if (contaOrigem.length !== 10 || !/^\d{10}$/.test(contaOrigem)) {
      return 'Conta de Origem deve conter exatamente 10 dígitos numéricos.'
    }
    if (contaDestino.length !== 10 || !/^\d{10}$/.test(contaDestino)) {
      return 'Conta de Destino deve conter exatamente 10 dígitos numéricos.'
    }
    if (contaOrigem === contaDestino) {
      return 'Conta de Origem e Destino não podem ser iguais.'
    }
    if (!valor || parseFloat(valor) <= 0) {
      return 'Valor deve ser maior que zero.'
    }
    if (!dataTransferencia) {
      return 'Data de Transferência é obrigatória.'
    }
    return null
  }
  async function agendar() {
    const erroValidacao = validar()
    if (erroValidacao) {
      erro.value = erroValidacao
      return false
    }
    enviando.value = true
    erro.value = null
    sucesso.value = null
    try {
      const payload = {
        contaOrigem: form.value.contaOrigem,
        contaDestino: form.value.contaDestino,
        valor: form.value.valor,
        dataTransferencia: form.value.dataTransferencia,
      }
      await transferenciaService.agendar(payload)
      sucesso.value = 'Agendamento realizado com sucesso!'
      form.value = {
        contaOrigem: '',
        contaDestino: '',
        valor: '',
        dataTransferencia: '',
      }
      return true
    } catch (e) {
      erro.value = e.message
      return false
    } finally {
      enviando.value = false
    }
  }
  function limparMensagens() {
    erro.value = null
    sucesso.value = null
  }
  return { form, enviando, sucesso, erro, dataMinima, agendar, limparMensagens }
}
