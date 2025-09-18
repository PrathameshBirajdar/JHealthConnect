<template>
  <div class="home">
    <h1>Welcome to JHealthConnect</h1>
    <p>Your one-stop platform to book doctor appointments and manage insurance.</p>

    <div class="cards">
      <div class="card" @click="$router.push('/doctors')">
        <h2>👨‍⚕️ Find Doctors</h2>
        <p>Browse specialists and book appointments easily.</p>
      </div>

      <div class="card" @click="$router.push('/appointments')">
        <h2>📅 Appointments</h2>
        <p>Check your scheduled visits and manage bookings.</p>
      </div>

      <div class="card" @click="$router.push('/insurance')">
        <h2>💳 Insurance</h2>
        <p>Submit and track your insurance claims in one place.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>
.home {
  padding: 20px;
}
.cards {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}
.card {
  background: #f5f5f5;
  padding: 15px;
  border-radius: 10px;
  cursor: pointer;
  flex: 1;
  transition: 0.3s;
}
.card:hover {
  background: #e0e0e0;
}
</style>
