const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/internships');

// Schema and Model
const InternshipSchema = new mongoose.Schema({
  title: String,
  company: String,
  details: String,
  imageUrl: String,
  applicants: Number,
});

const Internship = mongoose.model('Internship', InternshipSchema);

// Search API
app.get('/search', async (req, res) => {
  const query = req.query.q || '';
  const regex = new RegExp(query, 'i');
  try {
    const results = await Internship.find({
      $or: [{ title: regex }, { company: regex }],
    });
    res.json(results);
  } catch (err) {
    res.status(500).json({ error: 'Failed to fetch internships' });
  }
});

app.listen(3000, () => {
  console.log('Server started on http://localhost:3000');
});
