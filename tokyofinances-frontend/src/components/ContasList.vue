<template>
  <section class="card">
    <div class="card-header">
      <h2>Contas Disponíveis</h2>
      <button class="btn btn-secondary" :disabled="carregando" @click="buscarContas">
        {{ carregando ? 'Carregando...' : 'Atualizar' }}
      </button>
    </div>

    <div v-if="erro" class="alert alert-error">{{ erro }}</div>

    <div v-if="carregando" class="loading">Carregando contas...</div>

    <div v-else-if="contas.length === 0" class="empty-state">
      Nenhuma conta encontrada. Clique em "Atualizar" para buscar.
    </div>

    <div v-else class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>Nº Conta</th>
            <th>Titular</th>
            <th>Saldo (R$)</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="conta in contas" :key="conta.numeroConta || conta.id">
            <td class="mono">{{ conta.numeroConta }}</td>
            <td>{{ conta.titular || '—' }}</td>
            <td class="mono">{{ formatarValor(conta.saldo) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script setup>
import { onMounted } from 'vue'
import { useContas } from '@/composables/useContas'

const { contas, carregando, erro, buscarContas } = useContas()

function formatarValor(valor) {
  if (valor == null) return '—'
  return Number(valor).toLocaleString('pt-BR', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })
}

onMounted(() => {
  buscarContas()
})
</script>

<style scoped>
.mono {
  font-family: 'Courier New', monospace;
  font-weight: 600;
}
</style>
