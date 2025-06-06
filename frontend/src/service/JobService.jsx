
const BaseURL = 'http://localhost:8080/api/v1/jobs'

const getAllJobs = async(limit) => {
    const url = limit != null ? `${BaseURL}/all?_limit=${limit}` : `${BaseURL}/all`
    const res = await fetch(url)
    const data = await res.json()
    return data;
}

const createJob = async(newJob) => {
    const url = `${BaseURL}/create`
    await fetch(url, {
        method : 'POST',
        headers: {
          'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newJob)
        })
}

const getJobById = async (id) => {
  const res = await fetch(`${BaseURL}/${id}`);
    const data = await res.json()
    return data;
};

const updateJob = async (updatedJob, id) => {
  await fetch(`${BaseURL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(updatedJob),
  });
//   const data = await res.json()
//   return data
  return;
};

const deleteJobById = async (id) => {
  await fetch(`${BaseURL}/${id}`, {
    method: 'DELETE',
  });
};

export {getAllJobs, getJobById, createJob, updateJob, deleteJobById}