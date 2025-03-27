export default function FeatureCard({ title, description }) {
    return (
      <div className="bg-gray-200 p-6 rounded-lg shadow-md w-80">
        <h4 className="text-lg font-bold">{title}</h4>
        <p className="text-gray-600 mt-2">{description}</p>
      </div>
    );
  }