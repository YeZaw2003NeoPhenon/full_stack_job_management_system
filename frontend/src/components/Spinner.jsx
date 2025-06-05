import { ClipLoader } from "react-spinners";

const Spinner = ({isLoading}) => {
    const override = {
        display: 'block',
        margin: '100px auto'
    }
  return (
    <ClipLoader
        color='#438co'
        loading={isLoading}
        cssOverride={override}
        size={150}
        aria-label="Loading Spinner"
        data-testid="loader"
    />
  )
}

export default Spinner