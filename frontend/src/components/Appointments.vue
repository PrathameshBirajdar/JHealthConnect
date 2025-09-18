<template>
  <div>
    <h1>Book Appointment</h1>
    <form @submit.prevent="bookAppointment">
      <label>Patient Name:</label>
      <input v-model="form.name" required />

      <label>Date:</label>
      <input type="date" v-model="form.date" required />

      <label>Doctor:</label>
      <select v-model="form.doctor">
        <option disabled value="">Select Doctor</option>
        <option v-for="doc in doctors" :key="doc.id" :value="doc.name">{{ doc.name }}</option>
      </select>

      <button type="submit">Book</button>
    </form>

    <h2>Upcoming Appointments</h2>
    <ul>
      <li v-for="(a, i) in appointments" :key="i">
        {{ a.date }} - {{ a.name }} with {{ a.doctor }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Appointments",
  data() {
    return {
      form: { name: "", date: "", doctor: "" },
      doctors: [
        { id: 1, name: "Dr. John Smith" },
        { id: 2, name: "Dr. Sarah Lee" },
        { id: 3, name: "Dr. Amit Patel" }
      ],
      appointments: []
    }
  },
  methods: {
    bookAppointment() {
      this.appointments.push({ ...this.form })
      this.form = { name: "", date: "", doctor: "" }
    }
  }
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin: 15px 0;
}
button {
  background: #42b983;
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
}
button:hover {
  background: #2c9e70;
}
</style>
