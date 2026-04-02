<template>
  <section class="card">
    <h2>Agendar Transferência</h2>

    <div v-if="sucesso" class="alert alert-success">{{ sucesso }}</div>
    <div v-if="erro" class="alert alert-error">{{ erro }}</div>

    <form class="form" @submit.prevent="handleSubmit">
      <div class="form-row">
        <div class="form-group">
          <label for="contaOrigem">Conta de Origem</label>
          <select id="contaOrigem" v-model="form.contaOrigem" required @change="limparMensagens">
            <option value="" disabled>Selecione a conta de origem</option>
            <option
              v-for="conta in contasDisponiveis"
              :key="'orig-' + conta.numeroConta"
              :value="conta.numeroConta"
              :disabled="conta.numeroConta === form.contaDestino"
            >
              {{ conta.numeroConta }} {{ conta.titular ? `- ${conta.titular}` : '' }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label for="contaDestino">Conta de Destino</label>
          <select id="contaDestino" v-model="form.contaDestino" required @change="limparMensagens">
            <option value="" disabled>Selecione a conta de destino</option>
            <option
              v-for="conta in contasDisponiveis"
              :key="'dest-' + conta.numeroConta"
              :value="conta.numeroConta"
              :disabled="conta.numeroConta === form.contaOrigem"
            >
              {{ conta.numeroConta }} {{ conta.titular ? `- ${conta.titular}` : '' }}
            </option>
          </select>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label for="valor">Valor (R$)</label>
          <input
            id="valor"
            v-model="form.valor"
            type="number"
            step="0.01"
            min="0.01"
            placeholder="0.00"
            required
            @input="limparMensagens"
          />
        </div>

        <div class="form-group">
          <label for="dataTransferencia">Data da Transferência</label>
          <input
            id="dataTransferencia"
            v-model="form.dataTransferencia"
            type="date"
            :min="dataMinima"
            required
            @change="limparMensagens"
          />
        </div>
      </div>

      <button class="btn btn-primary" type="submit" :disabled="enviando">
        {{ enviando ? 'Agendando...' : 'Agendar Transferência' }}
      </button>
    </form>
  </section>
</template>

<script setup>
import { useAgendamento } from '@/composables/useAgendamento'

const props = defineProps({
  contasDisponiveis: {
    type: Array,
    default: () => [],
  },
})

const emit = defineEmits(['agendamento-realizado'])

const { form, enviando, sucesso, erro, dataMinima, agendar, limparMensagens } = useAgendamento()

async function handleSubmit() {
  const ok = await agendar()
  if (ok) {
    emit('agendamento-realizado')
  }
}
</script>

<style scoped>
.form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
}

@media (max-width: 640px) {
  .form-row {
    grid-template-columns: 1fr;
  }
}
</style>
