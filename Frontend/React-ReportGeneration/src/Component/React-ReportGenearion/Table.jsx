import React from 'react';

const Table = ({ table }) => {
    return (
        <div className="card shadow p-4 bg-white rounded">
            <h3 className="text-center mb-4">Courses List</h3>
            <table className="table table-hover table-bordered">
                <thead className="table-dark">
                    <tr>
                        <th scope="col">Course Id</th>
                        <th scope="col">Course Name</th>
                        <th scope="col">Course Category</th>
                        <th scope="col">Course Mode</th>
                        <th scope="col">Faculty Name</th>
                        <th scope="col">Location</th>
                        <th scope="col">Admin Name</th>
                        <th scope="col">Admin Contact</th>
                        <th scope="col">Course Status</th>
                        <th scope="col">Course Start Date</th>
                    </tr>
                </thead>
                <tbody>
                    {table.length === 0 ? (
                        <tr>
                            <td colSpan="10" className="text-center">No data available</td>
                        </tr>
                    ) : (
                        table.map((items) => (
                            <tr key={items.id}>
                                <th scope="row">{items.id}</th>
                                <td>{items.courseName}</td>
                                <td>{items.courseCategory}</td>
                                <td>{items.courseMode}</td>
                                <td>{items.facultyName}</td>
                                <td>{items.location}</td>
                                <td>{items.adminName}</td>
                                <td>{items.adminContact}</td>
                                <td>{items.courseStatus}</td>
                                <td>{items.startDate}</td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default Table;
