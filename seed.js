const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/internships');

const Internship = mongoose.model('Internship', new mongoose.Schema({
  title: String,
  company: String,
  details: String,
  imageUrl: String,
  applicants: Number,
}));

const sampleData = [
  {
    title: 'Android Development Intern',
    company: 'TechCorp',
    details: 'Remote · 3 months · Stipend ₹8000',
    imageUrl: 'https://via.placeholder.com/150',
    applicants: 34,
  },
  {
    title: 'Frontend Developer',
    company: 'InnovateX',
    details: 'On-site · 6 months · Stipend ₹10000',
    imageUrl: 'https://via.placeholder.com/150',
    applicants: 21,
  },
  {
    title: 'Machine Learning Intern',
    company: 'AI Labs',
    details: 'Remote · 4 months · No stipend',
    imageUrl: 'https://via.placeholder.com/150',
    applicants: 12,
  },
];

Internship.insertMany(sampleData)
  .then(() => {
    console.log('Data seeded');
    mongoose.connection.close();
  })
  .catch((err) => console.error('Seeding error:', err));
