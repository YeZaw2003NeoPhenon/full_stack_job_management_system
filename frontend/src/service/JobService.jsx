const BaseURL = 'http://localhost:8080/api/v1/jobs'

const getAllJobs = async(limit) => {
    try{
      const url = limit != null ? `${BaseURL}/all?_limit=${limit}` : `${BaseURL}/all`
      const res = await fetch(url)
      const data = await res.json()
      console.log("Fetched:", data)
      return data;
    }
    catch(error){
      console.log(`error ${error} while fetching job data`);
      return { data: [] }
    }
}

const getJobById = async (id) => {
  const res = await fetch(`${BaseURL}/${id}`);
  const data = await res.json()
  console.log(data.data);
  
  return data;
}; 

const getJobsByParam = async(query) => {
  const res = await fetch(`${BaseURL}/search?query=${encodeURIComponent(query)}`)
  const json = await res.json()
  return json;
}

const createJob = async(newJob) => {
    const url = `${BaseURL}/create`
    const res = await fetch(url, {
        method : 'POST',
        headers: {
          'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newJob)
      })
      const createdData = await res.json()
      return createdData;
}

const updateJob = async (updatedJob, id) => {
  const res = await fetch(`${BaseURL}/${id}`, {
                     method: 'PUT',
                  headers: {
                    'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(updatedJob),
               });
  const updatedData = await res.json()
  return updatedData;

};

const deleteJobById = async (id) => {
  await fetch(`${BaseURL}/${id}`, {
    method: 'DELETE',
  });
};

export {getAllJobs, getJobById, createJob, updateJob, deleteJobById, getJobsByParam}