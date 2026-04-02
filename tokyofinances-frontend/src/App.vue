<template>
  <div id="app">
    <AppHeader />

    <main class="main-content">
      <ContasList ref="contasListRef" />

      <AgendamentoForm
        :contas-disponiveis="contas ?? []"
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
import { onMounted, ref, computed } from 'vue'
import { useContas } from '@/composables/useContas'
import AppHeader from '@/components/AppHeader.vue'
import ContasList from '@/components/ContasList.vue'
import AgendamentoForm from '@/components/AgendamentoForm.vue'
import ExtratoTable from '@/components/ExtratoTable.vue'

const { contas, buscarContas } = useContas()

const contasListRef = ref(null)
const extratoRef = ref(null)

const anoAtual = computed(() => new Date().getFullYear())

function onAgendamentoRealizado() {
  extratoRef.value?.buscarExtrato()
}

onMounted(() => {
  buscarContas()
})
</script>
