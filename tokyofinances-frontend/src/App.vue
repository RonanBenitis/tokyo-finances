<template>
  <div id="app">
    <AppHeader />

    <main class="main-content">
      <ContasList ref="contasListRef" />

      <AgendamentoForm
        :contas-disponiveis="contasListRef?.contas ?? []"
        @agendamento-realizado="onAgendamentoRealizado"
      />

      <ExtratoTable ref="extratoRef" />
    </main>

    <footer class="app-footer">
      <p>Tokyo Finances &copy; {{ anoAtual }} — POC de Agendamento de Transferências</p>
    </footer>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import ContasList from '@/components/ContasList.vue'
import AgendamentoForm from '@/components/AgendamentoForm.vue'
import ExtratoTable from '@/components/ExtratoTable.vue'

const contasListRef = ref(null)
const extratoRef = ref(null)

const anoAtual = computed(() => new Date().getFullYear())

function onAgendamentoRealizado() {
  extratoRef.value?.buscarExtrato()
}
</script>
