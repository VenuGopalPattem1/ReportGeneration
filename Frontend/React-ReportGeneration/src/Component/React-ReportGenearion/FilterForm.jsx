import React from 'react';
import { useFormik } from 'formik';

const FilterForm = ({ data, data1, data2, onSubmit, clearFilters }) => {
    const formik = useFormik({
        initialValues: {
            courseCategory: null,
            faculty: null,
            courseMode: null
        },
        onSubmit: (values) => {
            onSubmit(values);
        }
    });

    return (
        <form onSubmit={formik.handleSubmit} className="row g-3">
            <div className="col-md-4">
                <label className="form-label">Select Course Modes</label>
                <select
                    name="courseMode"
                    value={formik.values.courseMode}
                    onChange={formik.handleChange}
                    className="form-select"
                >
                    <option value="">Clear</option>
                    {data.map((items, index) => (
                        <option key={index}>{items}</option>
                    ))}
                </select>
            </div>
            <div className="col-md-4">
                <label className="form-label">Select Faculty</label>
                <select
                    name="faculty"
                    value={formik.values.faculty}
                    onChange={formik.handleChange}
                    className="form-select"
                >
                    <option value="">Clear</option>
                    {data1.map((items, index) => (
                        <option key={index}>{items}</option>
                    ))}
                </select>
            </div>
            <div className="col-md-4">
                <label className="form-label">Select Courses</label>
                <select
                    name="courseCategory"
                    value={formik.values.courseCategory}
                    onChange={formik.handleChange}
                    className="form-select"
                >
                    <option value="">Clear</option>
                    {data2.map((items, index) => (
                        <option key={index}>{items}</option>
                    ))}
                </select>
            </div>
            <div className="col-md-12 text-center">
                <button type="submit" className="btn btn-primary me-3">Filter</button>
                <button type="button" className="btn btn-secondary" onClick={clearFilters}>Clear Filters</button>
            </div>
        </form>
    );
};

export default FilterForm;
