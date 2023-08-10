import React from "react";
import { Input } from "reactstrap";

export const SelectBoxCustom = ({
  data,
  selected,
  onSelectBoxChange,
  ...args
}) => {
  return (
    <Input
      {...args}
      type="select"
      onChange={(e) => {
        onSelectBoxChange(e.target.value);
      }}
    >
      <option value="default">default</option>
      {data.map((item) => (
        <option
          key={item.key}
          value={item.key}
          selected={selected === item.key}
        >
          {item.value}
        </option>
      ))}
    </Input>
  );
};
