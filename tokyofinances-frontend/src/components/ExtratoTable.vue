<template>
  <section class="card">
    <div class="card-header">
      <h2>Extrato de Agendamentos</h2>
      <button class="btn btn-secondary" :disabled="carregando" @click="buscarExtrato">
        {{ carregando ? 'Carregando...' : mostrarExtrato ? 'Atualizar' : 'Consultar Extrato' }}
      </button>
    </div>

    <div v-if="erro" class="alert alert-error">{{ erro }}</div>

    <template v-if="mostrarExtrato">
      <div v-if="carregando" class="loading">Carregando extrato...</div>

      <div v-else-if="extrato.length === 0" class="empty-state">Nenhum agendamento encontrado.</div>

      <div v-else class="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>Conta Origem</th>
              <th>Conta Destino</th>
              <th>Valor (R$)</th>
              <th>Taxa (R$)</th>
              <th>Data Transferência</th>
              <th>Data Agendamento</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in extrato" :key="item.id || index">
              <td class="mono">{{ item.contaOrigem.codigo }}</td>
              <td class="mono">{{ item.contaDestino.codigo }}</td>
              <td class="mono valor">{{ formatarValor(item.valor) }}</td>
              <td class="mono taxa">{{ formatarValor(item.taxa) }}</td>
              <td>{{ formatarData(item.dataTransferencia) }}</td>
              <td>{{ formatarData(item.dataAgendamento) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </template>
  </section>
</template>

<script setup>
import { ref } from 'vue'
import { useExtrato } from '@/composables/useExtrato'

const { extrato, carregando, erro, buscarExtrato: fetchExtrato } = useExtrato()
const mostrarExtrato = ref(false)

async function buscarExtrato() {
  mostrarExtrato.value = true
  await fetchExtrato()
}

function formatarValor(valor) {
  if (valor == null) return '—'
  return Number(valor).toLocaleString('pt-BR', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })
}

function formatarData(data) {
  if (!data) return '—'
  const d = new Date(data + 'T00:00:00')
  if (isNaN(d.getTime())) return data
  return d.toLocaleDateString('pt-BR')
}

defineExpose({ buscarExtrato })
</script>

<style scoped>
.mono {
  font-family: 'Courier New', monospace;
  font-weight: 600;
}

.valor {
  color: #0f3460;
}

.taxa {
  color: #c0392b;
}
</style>
