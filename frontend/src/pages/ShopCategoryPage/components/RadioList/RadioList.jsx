import React from "react";
import { Input } from "reactstrap";
import "./RadioList.css";

export const RadioList = ({ data, selected, onSelect }) => {
  return (
    <ul className="radio-list">
      {data.map((item, index) => (
        <li key={index} className="radio-list__item">
          <Input
            type="radio"
            id={item.id}
            name={item.type}
            value={item.id}
            selected={selected === item.id}
            onChange={(e) => {
              onSelect(parseInt(e.target.value));
            }}
            className="me-3"
          />
          <label htmlFor={item.id}>
            {item.name}
            <span> ({item.quantity})</span>
          </label>
        </li>
      ))}
    </ul>
  );
};
