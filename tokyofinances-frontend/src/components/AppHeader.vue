<template>
  <header class="app-header">
    <div class="header-content">
      <div class="logo">
        <span class="logo-icon">🏯</span>
        <h1>Tokyo Finances</h1>
      </div>
      <p class="tagline">Sistema de Agendamento de Transferências Financeiras</p>
    </div>
  </header>
</template>

<style scoped>
.app-header {
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
  color: #fff;
  padding: 1.5rem 2rem;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.3);
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
}

.logo {
  display: flex;
  align-items: center;
  gap: 0.75rem;
}

.logo-icon {
  font-size: 2rem;
}

.logo h1 {
  margin: 0;
  font-size: 1.75rem;
  font-weight: 700;
  letter-spacing: -0.5px;
}

.tagline {
  margin: 0.25rem 0 0;
  font-size: 0.9rem;
  opacity: 0.75;
  font-weight: 300;
}
</style>
