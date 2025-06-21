import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchJob = () => {
    const[searchTerm, setSearchterm] =  useState("")

    const navigate = useNavigate()

    const handleSearchSubmit = (e) => {
        e.preventDefault();
      if (searchTerm.trim() !== ""){
        navigate(`/jobs?search=${encodeURIComponent(searchTerm)}`);
      }
    }

  return (
        <div className="flex items-center bg-white rounded-full ml-4 px-2 py-1 shadow-md focus-within:ring-2 focus-within:ring-indigo-500">
            <form onSubmit={handleSearchSubmit}>
            <input
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchterm(e.target.value)}
                placeholder="Search jobs..."
                className="rounded-md px-3 py-1 text-sm focus:outline-none"
              />
            </form>
        </div>
  )
}

export default SearchJob