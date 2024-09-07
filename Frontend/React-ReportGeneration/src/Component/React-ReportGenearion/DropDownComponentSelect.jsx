import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Pdf from './Pdf';
import Excel from './Excel';
import FilterForm from './FilterForm';
import Table from './Table';

function DropDownComponentSelect() {
    const [data, setData] = useState([]);
    const [data1, setData1] = useState([]);
    const [data2, setData2] = useState([]);
    const [table, setTable] = useState([]);
    const [downloadLink, setDownloadLink] = useState(null);
    const [xldata,setXldata] = useState(null);
    
    // Fetch data for course modes
    const courseMode = () => {
        axios({
            method: "get",
            url: 'http://localhost:8080/app/mode'
        }).then(res => {
            setData(res.data);
        });
    };

    // Fetch data for faculty
    const faculty = () => {
        axios({
            method: "get",
            url: 'http://localhost:8080/app/faculty'
        }).then(res => {
            setData1(res.data);
        });
    };

    // Fetch data for courses
    const courses = () => {
        axios({
            method: "get",
            url: 'http://localhost:8080/app/course'
        }).then(res => {
            setData2(res.data);
        });
    };

    // Fetch data for the table
    const tableData = () => {
        axios({
            method: 'get',
            url: `http://localhost:8080/app/getAll`,
        }).then(out => {
            setTable(out.data);
        });
    };


    //Fetch The Pdf Data 
    function fetchPDF(){
        const fetchAndCreateDownloadLink = async () => {
            try {
              const response = await axios({
                url: 'http://localhost:8080/app/pdf-all', // Your URL
                method: 'post',
                responseType: 'blob', // Important
              });
      
              // Create file link in browser's memory
              const href = URL.createObjectURL(response.data);
      
              // Set the download link in state
              setDownloadLink(href);
            } catch (error) {
              console.error('Error fetching PDF:', error);
            }
          };
      
          fetchAndCreateDownloadLink();
      
          // Cleanup function to revoke the object URL
          return () => {
            if (downloadLink) {
              URL.revokeObjectURL(downloadLink);
            }
          };
    }
      function fetchExcel(){
        const fetchAndCreateDownloadLink = async () => {
            try {
              const response = await axios({
                url: 'http://localhost:8080/app/xl-all', // Your URL
                method: 'post',
                responseType: 'blob', // Important for handling file downloads
              });
      
              // Create file link in browser's memory
              const href = URL.createObjectURL(response.data);
      
              // Set the download link in state
              setXldata(href);
            } catch (error) {
              console.error('Error fetching Excel report:', error);
            }
          };
      
          fetchAndCreateDownloadLink();
      
          // Cleanup function to revoke the object URL
          return () => {
            if (xldata) {
              URL.revokeObjectURL(xldata);
            }
          };
      }
    useEffect(() => {
        courseMode();
        faculty();
        courses();
        tableData();
        fetchExcel();
        fetchPDF();
    }, []);

    // Handle filter submission
    const handleFilterSubmit = (values) => {
        axios({
            method: 'post',
            url: `http://localhost:8080/app/filters`,
            data: values,
        }).then(out => {
            setTable(out.data);
        });

        //Load The Filterd Form Data Into The  Excel Documnet
        const getExcelData = async () => {
            try {
              const response = await axios({
                url: 'http://localhost:8080/app/xl', // Your URL
                method: 'post',
                data: values,
                responseType: 'blob', // Important for handling file downloads
              });
      
              // Create file link in browser's memory
              const href = URL.createObjectURL(response.data);
      
              // Set the download link in state
              setXldata(href);
            } catch (error) {
              console.error('Error fetching Excel report:', error);
            }
          };
      
          getExcelData();
      
        //   // Cleanup function to revoke the object URL
        //   return () => {
        //     if (xldata) {
        //       URL.revokeObjectURL(xldata);
        //     }
        //   };

         //Load The Filterd Form Data Into The PDf Documnet
        const fetchAndCreateDownloadLink = async () => {
            try {
              const response = await axios({
                url: 'http://localhost:8080/app/pdf', // Your URL
                method: 'post',
                data: values,
                responseType: 'blob', // Important
              });
      
              // Create file link in browser's memory
              const href = URL.createObjectURL(response.data);
      
              // Set the download link in state
              setDownloadLink(href);
            } catch (error) {
              console.error('Error fetching PDF:', error);
            }
          };
      
          fetchAndCreateDownloadLink();
      
        //   // Cleanup function to revoke the object URL
        //   return () => {
        //     if (downloadLink) {
        //       URL.revokeObjectURL(downloadLink);
        //     }
        //   };

    };

    // Clear filters and reload data
    const clearFilters = () => {
        tableData();
    };

    
    return (
        <div className="container-fluid bg-light min-vh-100 p-4">
            <div className="card shadow p-4 mb-5 bg-white rounded">
                <h2 className="text-center mb-4">Course Filters</h2>
                <FilterForm
                    data={data}
                    data1={data1}
                    data2={data2}
                    onSubmit={handleFilterSubmit}
                    clearFilters={clearFilters}
                />
            </div>

            <div className="d-flex justify-content-center mb-4">
                <div className="me-3"><Pdf pdfData={downloadLink}/></div>
                <div><Excel xldata={xldata} /></div>
            </div>

            <Table table={table} />
        </div>
    );
}

export default DropDownComponentSelect;
