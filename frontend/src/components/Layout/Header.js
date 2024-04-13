import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Badge from "@mui/material/Badge";
import Tooltip from "@mui/material/Tooltip";
import IconButton from "@mui/material/IconButton";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

export default function Header() {
  let [isOpaque, setIsOpaque] = useState(false);

  useEffect(() => {
    let offset = 50;

    function onScroll() {
      if (!isOpaque && window.scrollY > offset) {
        setIsOpaque(true);
      } else if (isOpaque && window.scrollY <= offset) {
        setIsOpaque(false);
      }
    }

    onScroll();
    window.addEventListener("scroll", onScroll, { passive: true });
    return () => {
      window.removeEventListener("scroll", onScroll, { passive: true });
    };
  }, [isOpaque]);

  const getItem = localStorage.getItem("cart");
  const dataArray = JSON.parse(getItem);

  const router = useNavigate();

  const handleClickCart = () => {
    router("shopping-cart");
  };

  return (
    <header
      className={
        "sticky top-0 z-40 w-full backdrop-blur flex-none transition-colors duration-500 lg:z-50 lg:border-b border-slate-50/[0.06] " +
        (isOpaque
          ? "supports-backdrop-blur:bg-white/95 bg-slate-900/75"
          : "supports-backdrop-blur:bg-white/60 bg-transparent")
      }
    >
      <div className="max-w-8xl mx-auto">
        <div className="py-4 border-b lg:px-8 lg:border-0 border-slate-300/10 mx-4 lg:mx-0">
          <div className="flex justify-between">
            <div className={"flex justify-start gap-4 items-end"}>
              <Link to="/" className="font-bold text-3xl text-blue-500">Invoice Management</Link>
              <Link
                to="/order"
                className="text-gray-300 text-2xl font-semibold"
              >
                Orders
              </Link>
              <IconButton aria-label="cart" onClick={handleClickCart}>
                <Tooltip title="shopping-cart">
                  <Badge badgeContent={dataArray?.length} color="secondary">
                    <ShoppingCartIcon color="primary" />
                  </Badge>
                </Tooltip>
              </IconButton>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
}
