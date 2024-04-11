/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        white: '#F9F7F7',
        blue: '#3F72AF',
        navy: '#112D4E',
        arctic: '#DBE2EF',
        arctic_highlight: '#b9c6df'
      }
    },
  },
  plugins: [],
}
