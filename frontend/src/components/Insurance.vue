<template>
  <div>
    <h1>Insurance Claims</h1>
    <form @submit.prevent="submitClaim">
      <label>Policy Number:</label>
      <input v-model="form.policy" required />

      <label>Amount:</label>
      <input type="number" v-model="form.amount" required />

      <button type="submit">Submit Claim</button>
    </form>

    <h2>Submitted Claims</h2>
    <ul>
      <li v-for="(c, i) in claims" :key="i">
        Policy {{ c.policy }} - ₹{{ c.amount }} - Status: {{ c.status }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Insurance",
  data() {
    return {
      form: { policy: "", amount: "" },
      claims: []
    }
  },
  methods: {
    submitClaim() {
      this.claims.push({ ...this.form, status: "Pending" })
      this.form = { policy: "", amount: "" }
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
  background: #3498db;
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
}
button:hover {
  background: #217dbb;
}
</style>
