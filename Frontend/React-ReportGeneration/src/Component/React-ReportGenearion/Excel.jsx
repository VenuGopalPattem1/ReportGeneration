import axios from 'axios';
import React, { useEffect, useState } from 'react'

function Excel({xldata}) {
    const [downloadLink, setDownloadLink] = useState(null);

    // useEffect(() => {
    
    // }, []); // Include downloadLink in dependency array
  
    return (
      <div>
        {xldata ? (
          <a href={xldata}  className=' btn btn-success' download="Courses.xls" id="excel-download-link">
            Download Excel Report
          </a>
        ) : (
          <p>Loading...</p>
        )}
      </div>
    );
  };
  

export default Excel