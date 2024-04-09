import { Outlet } from 'react-router-dom';
import Header from "./Header";
import Footer from "./Footer";

export default function Layout() {
  return (
    <div className='flex flex-col min-h-screen antialiased text-slate-400 bg-blue-400'>
      <Header />
      <div className='mx-8 flex-grow my-1'>
        <Outlet />
      </div>
      <Footer />
    </div>
  );
}