import FeatureCard from "./FeatureCard";

export default function Features() {
  return (
    <section className="py-16 px-6 text-center">
      <h3 className="text-3xl font-bold text-blue-700">Key Features</h3>
      <div className="mt-8 flex flex-col md:flex-row gap-6 justify-center">
        <FeatureCard title="Task Organization" description="Easily create, assign, and track tasks across your team." />
        <FeatureCard title="Real-time Collaboration" description="Work together seamlessly with instant updates and comments." />
        <FeatureCard title="Analytics Dashboard" description="Gain insights into your team's productivity and project progress." />
      </div>
    </section>
  );
}