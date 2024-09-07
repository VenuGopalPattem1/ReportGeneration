import axios from 'axios'
import React, { useEffect, useState } from 'react'

function Pdf({pdfData}) {

    // useEffect(() => {
    //     const fetchAndCreateDownloadLink = async () => {
    //       try {
    //         const response = await axios({
    //           url: 'http://localhost:8080/app/pdf-all', // Your URL
    //           method: 'post',
    //           responseType: 'blob', // Important
    //         });
    
    //         // Create file link in browser's memory
    //         const href = URL.createObjectURL(response.data);
    
    //         // Set the download link in state
    //         setDownloadLink(href);
    //       } catch (error) {
    //         console.error('Error fetching PDF:', error);
    //       }
    //     };
    
    //     fetchAndCreateDownloadLink();
    
    //     // Cleanup function to revoke the object URL
    //     return () => {
    //       if (downloadLink) {
    //         URL.revokeObjectURL(downloadLink);
    //       }
    //     };
    //   }, []); // Empty dependency array means this runs once on mount
    
      return (
        <div>
          {pdfData ? (
            <a href={pdfData} className=' btn btn-danger' download="Courses.pdf" id="pdf-download-link">
              Download PDF
            </a>
          ) : (
            <p>Loading...</p>
          )}
        </div>
      );
    };

export default Pdf